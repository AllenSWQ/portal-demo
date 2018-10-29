/**
 * Bootstrap Table Chinese translation
 * Author: Zhixin Wen<wenzhixin2010@gmail.com>
 */
(function ($) {
    'use strict';

    $.fn.bootstrapTable.locales['en-US'] = {
        formatLoadingMessage: function () {
            return 'Trying to load the data, please wait a moment……';
        },
        formatRecordsPerPage: function (pageNumber) {
            return ' , show ' + pageNumber + ' in one page.';
        },
        formatShowingRows: function (pageFrom, pageTo, totalRows) {
            return 'Show ' + pageFrom + ' - ' + pageTo + ' , Total ' + totalRows;
        },
        formatSearch: function () {
            return 'Search';
        },
        formatNoMatches: function () {
            return 'No matching records';
        },
        formatPaginationSwitch: function () {
            return 'Hide/Show Pagination';
        },
        formatRefresh: function () {
            return 'Refresh';
        },    
        formatToggle: function () {
            return 'Switch';
        },
        formatColumns: function () {
            return 'Column';
        },
        formatExport: function () {
            return 'Export Data';
        },
        formatClearFilters: function () {
            return 'Empty';
        }
    };

    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['en-US']);

})(jQuery);
